package pl.edu.pk.hms.notifications.sender.sms

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "sms-gateway",
    url = "\${sms.gateway.url}"
)
fun interface SmsGateway {
    @PostMapping(value = ["\${sms.gateway.path}"])
    fun send(
        @RequestParam("username") login: String,
        @RequestParam("password") password: String,
        @RequestParam("number") text: String,
        @RequestParam("text") number: String
    )
}
