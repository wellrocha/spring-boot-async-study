package br.com.wellrocha.spring_boot_async_study.adapter.sync

import br.com.wellrocha.spring_boot_async_study.dto.Names
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GetNamesApiAdapterSync(
    private val restTemplate: RestTemplate
) {
    private companion object {
        private val logger: Logger = LoggerFactory.getLogger(GetNamesApiAdapterSync::class.java)
    }

    fun execute(): Array<Names>? =
        try {
            restTemplate
                .getForObject("http://localhost:8085/names", Array<Names>::class.java)
        } catch (exception: Exception) {
            logger.error("${this.javaClass.simpleName}.execute Unknown error [$exception]")
            null
        }
}