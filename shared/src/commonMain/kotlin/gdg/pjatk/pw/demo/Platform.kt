package gdg.pjatk.pw.demo

interface Platform {
    val name: String
}



expect fun getPlatform(): Platform