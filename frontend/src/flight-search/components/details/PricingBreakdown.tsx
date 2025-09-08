import {Card, CardContent} from "@/components/ui/card";
import {Separator} from "@/components/ui/separator";
import {PricingDisplay} from "../shared/PricingDisplay";
import {formatCurrency} from "@/lib/utils";
import type {FlightOffer} from "../../types/flights/flight-offer.type";

interface Props {
  flightOffer: FlightOffer;
}

export function PricingBreakdown({flightOffer}: Props) {
  return (
    <Card>
      <CardContent className="space-y-4">
        <div className="mb-4 text-lg font-semibold">Price Breakdown</div>

        <PricingDisplay
          grandTotalPrice={flightOffer.grandTotalPrice}
          perTravelerPrice={flightOffer.travelerPricings[0].totalPrice}
          currency={flightOffer.currency}
          alignment="right"
        />

        <div className="space-y-2 text-sm">
          <div className="flex justify-between">
            <span className="text-muted-foreground">Base price</span>
            <span>
              {formatCurrency(flightOffer.basePrice, flightOffer.currency)}
            </span>
          </div>

          {flightOffer.fees.map((fee, index) => (
            <div
              key={index}
              className="flex justify-between"
            >
              <span className="text-muted-foreground capitalize">
                {fee.type.toLowerCase()}
              </span>
              <span>{formatCurrency(fee.amount, flightOffer.currency)}</span>
            </div>
          ))}

          <Separator />

          <div className="space-y-1">
            {flightOffer.travelerPricings.map((pricing) => (
              <div
                key={pricing.travelerId}
                className="flex justify-between"
              >
                <span className="text-muted-foreground">
                  Traveler {pricing.travelerId}
                </span>
                <span>
                  {formatCurrency(pricing.totalPrice, flightOffer.currency)}
                </span>
              </div>
            ))}
          </div>
        </div>
      </CardContent>
    </Card>
  );
}
