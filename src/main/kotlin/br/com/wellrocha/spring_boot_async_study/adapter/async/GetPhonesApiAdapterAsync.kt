package br.com.wellrocha.spring_boot_async_study.adapter.async

import br.com.wellrocha.spring_boot_async_study.dto.Phones
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture

@Service
class GetPhonesApiAdapterAsync(
    private val restTemplate: RestTemplate
) {
    private companion object {
        private val logger: Logger = LoggerFactory.getLogger(GetPhonesApiAdapterAsync::class.java)
    }

    @Async("asyncExecutor")
    fun execute(): CompletableFuture<Array<Phones>?> =
        try {
            CompletableFuture.completedFuture(restTemplate
                .getForObject("http://localhost:8085/phones", Array<Phones>::class.java)
                .also {
                    logger.info("${this.javaClass.simpleName}.execute Success")
                })
        } catch (exception: Exception) {
            logger.error("${this.javaClass.simpleName}.execute Unknown error [$exception]")
            CompletableFuture.completedFuture(null)
        }
}