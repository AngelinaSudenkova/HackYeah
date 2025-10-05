import requests
import json

# Define the Overpass API endpoint
OVERPASS_URL = "https://overpass-api.de/api/interpreter"
NOMINATIM_URL = "https://nominatim.openstreetmap.org/search"


# Define the Overpass QL query to find all 'amenity=cafe' within a bounding box
# Bounding box is defined as (south, west, north, east)
# This example covers a small area in Paris, France.

get_place_suggestions_declaration = {
    "name": "get_place_suggestions",
    "description": (
        "Performs a search for places and activities on OpenStreetMap based on a provided "
        "list of key-value tags. This function handles the final API call to the Overpass "
        "service and is designed to find specific points of interest (e.g., 'restaurants') or "
        "geographical features (e.g., 'beaches'). It is the final step after the user's "
        "natural language query has been translated into OpenStreetMap tags."
    ),
    "parameters": {
        "type": "object",
        "properties": {
            "osm_tags": {
                "type": "object",
                "properties": {
                    "tags": {
                        "type": "array",
                        "items": {
                            "type": "object",
                            "properties": {
                                "key": {"type": "string"},
                                "value": {"type": "string"}
                            },
                            "required": ["key", "value"]
                        },
                        "description": "A list of key-value dictionaries representing the OpenStreetMap tags to query. For example, to find restaurants, the tags would be [{'key': 'amenity', 'value': 'restaurant'}].",
                    },
                    "geographical_constraints": {
                        "type": "array",
                        "items": {"type": "string"},
                        "description": "A list of geographical names (e.g., countries, cities, or states) to limit the search area.",
                    },
                },
                "required": ["tags"],
                "description": "A dictionary containing the OpenStreetMap tags and optional geographical constraints for the search."
            }
        },
        "required": ["osm_tags"],
    },
}
def get_place_suggestions(osm_tags: list[dict]):
    tags_list = osm_tags.get("tags", [])
    geographical_constraints = osm_tags.get("geographical_constraints", [])
    tag_queries = []
    if geographical_constraints:
        params = {
            'q': geographical_constraints[0],
            'format': 'json',
            'limit': 1  # Get only the most relevant result
        }
        headers = {
            'User-Agent': 'My-LLM-App-Name/1.0'
        }
        try:
            response = requests.get(NOMINATIM_URL, params=params, headers=headers)
            response.raise_for_status()
            
            data = response.json()

            # The bounding box is in the format [min_lat, max_lat, min_lon, max_lon]
            bbox_str = data[0]['boundingbox']
            
            # Convert the string values to floats
            bbox = [float(coord) for coord in bbox_str]
            
            print(bbox)
        except requests.exceptions.RequestException as e:
            print(f"Error fetching data from Nominatim: {e}")
            return None
        except (json.JSONDecodeError, KeyError) as e:
            print(f"Failed to parse Nominatim response: {e}")
            return None

    for tag in tags_list:
        key = tag['key']
        value = tag['value']
        tag_queries.append(f'  nwr["{key}"="{value}"]({bbox[0]},{bbox[2]},{bbox[1]},{bbox[3]});')
    print(tag_queries)
    overpass_query = f"""
    [out:json];
    (
    {"".join(tag_queries)}
    );
    out body;
    """

    data = {'data': overpass_query}

    try:
        response = requests.post(OVERPASS_URL, data=data)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.RequestException as e:
        return {"error": f"An error occurred: {e}"}
    except json.JSONDecodeError:
        return {"error": "Failed to decode JSON from the response."}

    


# def query_place_objects(places: list[]):
#     overpass_query = """
#     [out:json];
#     (
#     node["amenity"="cafe"](50.7,7.1,50.8,7.25);
#     way["amenity"="cafe"](50.7,7.1,50.8,7.25);
#     relation["amenity"="cafe"](50.7,7.1,50.8,7.25);
#     );
#     out body;
#     """

#     # Prepare the data to be sent in the POST request
#     data = {'data': overpass_query}

#     try:
#         # Send the POST request to the Overpass API
#         response = requests.post(OVERPASS_URL, data=data)

#         # Raise an HTTPError for bad responses (4xx or 5xx)
#         response.raise_for_status()

#         # Parse the JSON response
#         data = response.json()

#         # Process and print the results
#         print(f"Found {len(data['elements'])} cafes.")
#         for element in data['elements']:
#             if 'tags' in element and 'name' in element['tags']:
#                 name = element['tags']['name']
#                 lat = element.get('lat', 'N/A')
#                 lon = element.get('lon', 'N/A')
#                 print(f"Name: {name}, Lat: {lat}, Lon: {lon}")
#             else:
#                 print(f"Found an unnamed cafe with ID: {element['id']}")

#     except requests.exceptions.RequestException as e:
#         print(f"An error occurred: {e}")
#     except json.JSONDecodeError:
#         print("Failed to decode JSON from the response.")

