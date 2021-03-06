package io.toolisticon.addons.axon.examples.giftcard

import io.toolisticon.addons.axon.examples.giftcard.api.GiftcardCommand.IssueCommand
import io.toolisticon.addons.axon.examples.giftcard.api.GiftcardCommand.RedeemCommand
import io.toolisticon.addons.axon.examples.giftcard.api.GiftcardEvent.IssuedEvent
import io.toolisticon.addons.axon.examples.giftcard.api.GiftcardEvent.RedeemedEvent
import io.toolisticon.addons.axon.examples.giftcard.domain.GiftcardAggregate
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.Test

class GiftcardSampleTest {

  val fixture = AggregateTestFixture(GiftcardAggregate::class.java)

  @Test
  fun `issue cmd creates new aggregate`() {
    fixture.givenNoPriorActivity()
      .`when`(IssueCommand("1", 100))
      .expectEvents(IssuedEvent("1", 100))
  }

  @Test
  fun `redeem 50 from card`() {
    fixture.given(IssuedEvent("1", 100))
      .`when`(RedeemCommand("1", 50))
      .expectEvents(RedeemedEvent("1", 50))
  }
}
