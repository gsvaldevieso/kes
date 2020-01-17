import Model.BankAccount
import Model.DomainException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.util.*

class BankAccountAggregateTest {
    @Test
    fun balanceShouldBeValid()
    {
        val b = BankAccount(UUID.randomUUID())
        b.deposit(BigDecimal(85))
        b.withdraw(BigDecimal(10))
        b.deposit(BigDecimal(20))
        b.withdraw(BigDecimal(5))
        Assertions.assertEquals(BigDecimal(90), b.balance)
    }

    @Test
    fun withdrawShouldNotWorkWhenNoFunds()
    {
       val b = BankAccount(UUID.randomUUID())
        assertThrows<DomainException>("you have no funds to perfom this operation", { b.withdraw(BigDecimal(1)) })
    }
}