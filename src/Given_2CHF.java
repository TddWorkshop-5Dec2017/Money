import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Given_2CHF
{

    final Money CHF_2 = new Money(new BigDecimal(2.0), "CHF");

    @Test
    public void when_added_3_CHF_then_returns_5_CHF() throws CurrencyMismatchException
    {
        Money CHF_3 = new Money(new BigDecimal(3.0), "CHF");
        Money CHF_5 = new Money(new BigDecimal(5.0), "CHF");
        Money ACTUAL_SUM = CHF_2.add(CHF_3);
        assertThat(ACTUAL_SUM, is(equalTo(CHF_5)));
    }

    @Test
    public void when_added_3_USD_returns_correct_value_in_CHF()
    {
        Money USD_3 = new Money(new BigDecimal(3.0), "USD");
        Money CHF_8 = new Money(new BigDecimal(8.0), "CHF");
        CHF_2.exchangeRateService = new StubExchangeRateService();
        Money ACTUAL_SUM = CHF_2.add(USD_3);
        assertThat(ACTUAL_SUM, is(equalTo(CHF_8)));
    }

    @Test
    public void when_added_negative_3_CHF_then_returns_negative_1_CHF() throws CurrencyMismatchException
    {
        Money CHF_negative_3 = new Money(new BigDecimal(-3.0), "CHF");
        Money CHF_negative_1 = new Money(new BigDecimal(-1.0), "CHF");
        Money ACTUAL_SUM = CHF_2.add(CHF_negative_3);
        assertThat(ACTUAL_SUM, is(equalTo(CHF_negative_1)));
    }

    @Test
    public void when_added_3_333_CHF_then_returns_5_333_CHF() throws CurrencyMismatchException
    {
        Money CHF_3_333 = new Money(new BigDecimal(3.333), "CHF");
        Money CHF_5_333 = new Money(new BigDecimal(5.333), "CHF");
        Money ACTUAL_SUM = CHF_2.add(CHF_3_333);
        assertThat(ACTUAL_SUM, is(equalTo(CHF_5_333)));
    }

    private class StubExchangeRateService implements ExchangeRateService
    {
        @Override
        public double getRate(String fromCurrency, String toCurrency)
        {
            return 2.0;
        }
    }
}
