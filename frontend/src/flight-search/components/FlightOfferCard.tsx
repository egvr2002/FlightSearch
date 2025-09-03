import {Card, CardContent} from "@/components/ui/card";
import {Separator} from "@/components/ui/separator";

export function FlightOfferCard() {
  return (
    <Card className="hover:bg-primary/5 cursor-pointer transition-colors ease-in-out">
      <CardContent>
        <section className="grid grid-cols-12 gap-y-6">
          <div className="col-span-12 md:col-span-8">
            <FlightData className="grid grid-cols-12 gap-y-1" />
            <Separator className="col-span-12 my-6" />
            <FlightData className="grid grid-cols-12 gap-y-1" />
          </div>

          <FlightPricing className="justify-center-center col-span-12 flex h-full flex-col justify-center gap-4 md:col-span-4 md:border-l" />
        </section>
      </CardContent>
    </Card>
  );
}

function FlightData({...props}: React.ComponentProps<"div">) {
  return (
    <div {...props}>
      <h2 className="leading-non text-primary col-span-12 text-lg">
        {"San Francisco (SFO)"} - {"New York (JFK)"}
      </h2>
      <p className="col-span-12 md:col-span-4">
        {"1:40"} - {"10:37pm"}
      </p>
      <div className="text-muted-foreground col-span-12 text-sm font-normal md:col-span-8">
        <p>
          {"8h 17m"} {"(1 Stop)"}
        </p>
        <p>
          {"2h 17m"} in {"Los Angeles (LAX)"}
        </p>
      </div>
    </div>
  );
}

function FlightPricing({...props}: React.ComponentProps<"div">) {
  return (
    <div {...props}>
      <div className="text-primary text-right">
        <p className="font-bold">{"$1,500.00 MXN"}</p>
        <p className="text-sm">Total</p>
      </div>

      <div className="text-secondary text-right">
        <p>{"$1,500.00 MXN"}</p>
        <p className="text-sm">Per traveler</p>
      </div>
    </div>
  );
}
