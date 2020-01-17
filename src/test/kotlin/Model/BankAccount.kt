package Model

import com.valdevieso.AggregateRoot
import com.valdevieso.Event
import java.math.BigDecimal
import java.util.*

class BankAccount(private val id: UUID): AggregateRoot() {
    var balance: BigDecimal = BigDecimal.ZERO
        private set

    private fun whenDepositPerformed(event: DepositPerformed) {
        this.balance += event.amount
    }

    private fun whenWithdrawPerformed(event: WithdrawPerformed) {
        this.balance -= event.amount
    }

    private fun whenAccountCreated(event: AccountCreated) {
        this.balance = BigDecimal.ZERO
    }

    fun deposit(amount: BigDecimal)
    {
        this.applyChange(DepositPerformed(amount))
    }

    fun withdraw(amount: BigDecimal)
    {
        if (this.balance < amount) throw DomainException("you have no funds to perfom this operation")
        this.applyChange(WithdrawPerformed(amount))
    }

    override fun getId(): UUID {
        return this.id
    }

    override fun apply(event: Event) {
        when (event) {
            is DepositPerformed -> this.whenDepositPerformed(event)
            is AccountCreated -> this.whenAccountCreated(event)
            is WithdrawPerformed -> this.whenWithdrawPerformed(event)
        }
    }
}