import {Card, CardContent, CardFooter} from "@/components/ui/card";
import {Separator} from "@/components/ui/separator";
import {Skeleton} from "@/components/ui/skeleton";

export function FlightOfferCardSkeleton() {
  return (
    <Card>
      <CardContent>
        <div className="grid grid-cols-12 gap-y-6">
          {/* Itineraries Skeleton */}
          <div className="col-span-full space-y-6 md:col-span-8">
            {/* First Itinerary */}
            <div className="space-y-4">
              {/* Itinerary Header Skeleton */}
              <div className="flex items-center justify-between">
                <div className="flex items-center space-x-4">
                  <Skeleton className="h-6 w-16" />
                  <Skeleton className="h-4 w-12" />
                  <Skeleton className="h-6 w-16" />
                </div>
                <Skeleton className="h-4 w-20" />
              </div>

              {/* Duration Skeleton */}
              <div className="flex items-center space-x-4">
                <Skeleton className="h-4 w-20" />
                <Skeleton className="h-4 w-24" />
              </div>

              {/* Airline Info Skeleton */}
              <div className="flex items-center space-x-2">
                <Skeleton className="h-8 w-8" />
                <Skeleton className="h-4 w-32" />
              </div>
            </div>

            <Separator className="my-6" />

            {/* Second Itinerary (Return flight) */}
            <div className="space-y-4">
              {/* Itinerary Header Skeleton */}
              <div className="flex items-center justify-between">
                <div className="flex items-center space-x-4">
                  <Skeleton className="h-6 w-16" />
                  <Skeleton className="h-4 w-12" />
                  <Skeleton className="h-6 w-16" />
                </div>
                <Skeleton className="h-4 w-20" />
              </div>

              {/* Duration Skeleton */}
              <div className="flex items-center space-x-4">
                <Skeleton className="h-4 w-20" />
                <Skeleton className="h-4 w-24" />
              </div>

              {/* Airline Info Skeleton */}
              <div className="flex items-center space-x-2">
                <Skeleton className="h-8 w-8" />
                <Skeleton className="h-4 w-32" />
              </div>
            </div>
          </div>

          {/* Pricing Skeleton */}
          <div className="col-span-full space-y-4 self-center md:col-span-4">
            <div className="space-y-4 text-right">
              {/* Grand Total Price */}
              <div>
                <Skeleton className="mb-1 ml-auto h-6 w-24" />
                <Skeleton className="ml-auto h-4 w-12" />
              </div>

              {/* Per Traveler Price */}
              <div>
                <Skeleton className="mb-1 ml-auto h-5 w-20" />
                <Skeleton className="ml-auto h-4 w-16" />
              </div>
            </div>
          </div>
        </div>
      </CardContent>

      <CardFooter>
        <Skeleton className="h-10 w-full md:ml-auto md:w-32" />
      </CardFooter>
    </Card>
  );
}
