import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";
import {CheckCircle, XCircle} from "lucide-react";
import type {TravelerPricing} from "../types/flights/traveler-pricing.type";

interface Props {
  travelersPricings: TravelerPricing[];
  segmentId: string;
}

export function TravelerFareDetails({segmentId, travelersPricings}: Props) {
  const travelersWithFares = travelersPricings.map((pricing) => ({
    travelerId: pricing.travelerId,
    fareDetails: pricing.fareDetailsBySegment.find(
      (fd) => fd.segmentId === segmentId,
    ),
  }));

  return (
    <div className="space-y-6">
      <Accordion
        type="multiple"
        className="w-full"
      >
        {travelersWithFares.map(({fareDetails, travelerId}) => (
          <AccordionItem
            key={fareDetails?.segmentId}
            value={`traveler-${travelerId}`}
          >
            <AccordionTrigger>Traveler {travelerId}</AccordionTrigger>
            <AccordionContent>
              <div className="space-y-4 pl-2">
                <div className="flex items-center gap-6 text-sm">
                  <div>
                    <span className="text-muted-foreground">Cabin: </span>
                    <span className="font-medium">{fareDetails!.cabin}</span>
                  </div>
                  <div>
                    <span className="text-muted-foreground">Class: </span>
                    <span className="font-medium">{fareDetails!.class}</span>
                  </div>
                </div>

                <div className="space-y-2">
                  <div className="text-muted-foreground text-sm font-medium">
                    Amenities
                  </div>
                  <div className="grid grid-cols-1 gap-2 text-sm">
                    {fareDetails!.amenities.map((amenity) => (
                      <div
                        key={amenity.name}
                        className="flex items-center gap-2"
                      >
                        {amenity.isChargeable ? (
                          <XCircle className="size-3 text-red-500" />
                        ) : (
                          <CheckCircle className="size-3 text-green-500" />
                        )}
                        <span className="capitalize">
                          {amenity.name.toLowerCase()}
                        </span>
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            </AccordionContent>
          </AccordionItem>
        ))}
      </Accordion>
    </div>
  );
}
