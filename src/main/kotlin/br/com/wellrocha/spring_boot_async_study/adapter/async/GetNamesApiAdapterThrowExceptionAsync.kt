package br.com.wellrocha.spring_boot_async_study.adapter.async

import br.com.wellrocha.spring_boot_async_study.dto.Names
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture

@Service
class GetNamesApiAdapterThrowExceptionAsync() {
    private companion object {
        private val logger: Logger = LoggerFactory.getLogger(GetNamesApiAdapterThrowExceptionAsync::class.java)
    }

    @Async("asyncExecutor")
    fun execute(): CompletableFuture<Array<Names>?> {
        val errorMessage = "You don't have sufficient permissions to perform this action."
        logger.error("${this.javaClass.simpleName}.execute Unknown error [$errorMessage]")
        return CompletableFuture.failedFuture(RuntimeException(errorMessage))
    }
}