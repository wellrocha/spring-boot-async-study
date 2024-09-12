package br.com.wellrocha.spring_boot_async_study.adapter.sync

import br.com.wellrocha.spring_boot_async_study.dto.Addresses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GetAddressesApiAdapterSync(
    private val restTemplate: RestTemplate
) {
    private companion object {
        private val logger: Logger = LoggerFactory.getLogger(GetAddressesApiAdapterSync::class.java)
    }

    fun execute(): Array<Addresses>? =
        try {
            restTemplate
                .getForObject("http://localhost:8085/addresses", Array<Addresses>::class.java)
        } catch (exception: Exception) {
            logger.error("${this.javaClass.simpleName}.execute Unknown error [$exception]")
            null
        }
}