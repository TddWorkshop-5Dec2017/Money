import java.math.BigDecimal;

public class Money
{

    private BigDecimal amount;
    private String currency;
    public ExchangeRateService exchangeRateService;

    public Money(BigDecimal amount, String currency)
    {
        this.amount = amount;
        this.currency = currency;
        exchangeRateService = new BOAExchangeRateService();
    }

    @Override
    public String toString()
    {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (!amount.equals(money.amount))
        {
            return false;
        }
        return currency.equals(money.currency);

    }

    @Override
    public int hashCode()
    {
        int result = amount.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }

    public Money add(Money money)
    {
        if (!this.currency.equals(money.currency))
        {
            double currentRate = exchangeRateService.getRate(money.currency, this.currency);
            BigDecimal convertedAmount = money.amount.multiply(new BigDecimal(currentRate));
            return new Money(this.amount.add(convertedAmount), this.currency);
        }
        return new Money(this.amount.add(money.amount), this.currency);
    }
}
