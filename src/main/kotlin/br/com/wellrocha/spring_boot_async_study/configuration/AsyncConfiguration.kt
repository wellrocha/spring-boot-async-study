package br.com.wellrocha.spring_boot_async_study.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class AsyncConfiguration {
    @Bean("asyncExecutor")
    fun asyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 10
        executor.maxPoolSize = 100
        executor.queueCapacity = 200
        executor.setThreadNamePrefix("AsyncThread-")
        executor.initialize()
        return executor
    }
}