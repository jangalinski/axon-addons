@file:Suppress("unused")
package io.toolisticon.addons.axon.jgiven.saga

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.As
import com.tngtech.jgiven.annotation.ExpectedScenarioState
import com.tngtech.jgiven.annotation.ProvidedScenarioState
import com.tngtech.jgiven.annotation.Quoted
import io.toolisticon.addons.jgiven.JGivenKotlinStage
import org.axonframework.test.saga.FixtureExecutionResult
import org.axonframework.test.saga.WhenState
import java.time.Duration
import java.time.Instant

@JGivenKotlinStage
class SagaFixtureWhen<T> : Stage<SagaFixtureWhen<T>>() {

  @ExpectedScenarioState(required = true)
  lateinit var whenState: WhenState

  @ProvidedScenarioState
  lateinit var thenState: FixtureExecutionResult


  @As("when aggregate $ publishes event: $")
  fun aggregatePublishes(@Quoted aggregateIdentifier: String, event: Any): SagaFixtureWhen<T> = execute {
    whenState.whenAggregate(aggregateIdentifier).publishes(event)
  }

  fun timeAdvancesTo(date: Instant) = execute {
    whenState.whenTimeAdvancesTo(date)
  }

  fun publishing(event: Any): SagaFixtureWhen<T> = execute {
    whenState.whenPublishingA(event)
  }

  fun timeElapses(duration: Duration) = execute {
    whenState.whenTimeElapses(duration)
  }

  private fun execute(block: () -> FixtureExecutionResult) = self().apply {
    thenState = block.invoke()
  }!!

}
