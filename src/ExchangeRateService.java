/**
 * Created by steve on 12/5/17.
 */
public interface ExchangeRateService
{
    //    public Money convert(Money amount, String toCurrency) {
    //        return new Money(amo)
    //    }
    double getRate(String fromCurrency, String toCurrency);
}
