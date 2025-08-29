import {Button} from "@/components/ui/button";
import {Card, CardContent, CardHeader, CardTitle} from "@/components/ui/card";
import {Checkbox} from "@/components/ui/checkbox";
import {Input} from "@/components/ui/input";
import {Label} from "@/components/ui/label";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

export function FlightsSearchPage() {
  return (
    <Card className="w-full">
      <CardHeader>
        <CardTitle>Flight Search</CardTitle>
      </CardHeader>
      <CardContent>
        <form className="space-y-6">
          <div className="flex flex-col gap-1">
            <Label htmlFor="departureCode">Departure Code</Label>
            <Input
              name="departureCode"
              id="departureCode"
              type="text"
            />
          </div>

          <div className="flex flex-col gap-1">
            <Label htmlFor="arrivalCode">Arrival Code</Label>
            <Input
              name="arrivalCode"
              id="arrivalCode"
              type="text"
            />
          </div>

          <div className="flex flex-col gap-1">
            <Label htmlFor="departureDate">Departure date</Label>
            <Input
              name="departureDate"
              id="departureDate"
              type="date"
            />
          </div>

          <div className="flex flex-col gap-1">
            <Label htmlFor="returnDate">Return date</Label>
            <Input
              name="returnDate"
              id="returnDate"
              type="date"
            />
          </div>

          <div className="flex flex-col gap-1">
            <Label htmlFor="adults">Adults</Label>
            <Input
              name="adults"
              id="adults"
              type="number"
              min={0}
            />
          </div>

          <div className="flex flex-col gap-1">
            <Label htmlFor="currency">Currency</Label>
            <Select>
              <SelectTrigger className="w-full">
                <SelectValue placeholder="Select a value..." />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="MXN">MXN</SelectItem>
                <SelectItem value="USD">USD</SelectItem>
                <SelectItem value="EUR">EUR</SelectItem>
              </SelectContent>
            </Select>
          </div>

          <div className="flex gap-1">
            <Checkbox id="nonStop" />
            <Label htmlFor="nonStop">Non-stop</Label>
          </div>

          <Button
            className="w-full"
            type="submit"
          >
            Search Flights
          </Button>
        </form>
      </CardContent>
    </Card>
  );
}
