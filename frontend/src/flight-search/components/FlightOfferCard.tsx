import {Button} from "@/components/ui/button";
import {Card, CardContent, CardFooter} from "@/components/ui/card";
import {FlightItinerary} from "./FlightItinerary";
import {Separator} from "@/components/ui/separator";

import {ChevronRight} from "lucide-react";

export function FlightOfferCard() {
  return (
    <Card>
      <CardContent>
        <div className="grid grid-cols-12 gap-y-6">
          {/* Itineraries */}
          <div className="col-span-full space-y-6 md:col-span-8">
            <FlightItinerary />
            <Separator />
            <FlightItinerary />
          </div>

          {/* Pricing */}
          <div className="col-span-full space-y-4 self-center md:col-span-4">
            <div className="text-primary text-right font-semibold">
              <p>{"MXN 1,500.00"}</p>
              <p className="text-sm">total</p>
            </div>

            <div className="text-secondary text-right">
              <p>{"MXN 1,500.00"}</p>
              <p className="text-sm">per traveler</p>
            </div>
          </div>
        </div>
      </CardContent>

      <CardFooter>
        {/* Defailts Button */}
        <Button className="w-full md:ml-auto md:w-fit">
          View details
          <ChevronRight />
        </Button>
      </CardFooter>
    </Card>
  );
}
