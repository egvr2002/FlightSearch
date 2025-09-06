import {Form, useActionData} from "react-router";

import {Checkbox} from "../../../components/ui/checkbox";
import {Label} from "../../../components/ui/label";
import {
  Select,
  SelectTrigger,
  SelectValue,
  SelectContent,
  SelectItem,
} from "../../../components/ui/select";
import {Button} from "../../../components/ui/button";
import {Input} from "../../../components/ui/input";

import {FieldError} from "../../../components/shared/components/FieldError";
import {LocationCombobox} from "./LocationCombobox";
import {searchFlightsAction} from "@/flight-search/actions/search-flights.action";

export function SearchFlightsForm() {
  const actionData = useActionData<typeof searchFlightsAction>();
  const error = actionData?.error;

  return (
    <Form
      className="space-y-6"
      method="POST"
    >
      <div className="flex flex-col gap-1">
        <Label htmlFor="originLocationCode">Departure Code</Label>
        <LocationCombobox
          name="originLocationCode"
          id="originLocationCode"
        />
        <FieldError error={error?.originLocationCode?.errors[0]} />
      </div>

      <div className="flex flex-col gap-1">
        <Label htmlFor="destinationLocationCode">Arrival Code</Label>
        <LocationCombobox
          name="destinationLocationCode"
          id="destinationLocationCode"
        />
        <FieldError error={error?.destinationLocationCode?.errors[0]} />
      </div>

      <div className="flex flex-col gap-1">
        <Label htmlFor="departureDate">Departure date</Label>
        <Input
          name="departureDate"
          id="departureDate"
          type="date"
        />
        <FieldError error={error?.departureDate?.errors[0]} />
      </div>

      <div className="flex flex-col gap-1">
        <Label htmlFor="returnDate">Return date</Label>
        <Input
          name="returnDate"
          id="returnDate"
          type="date"
        />
        <FieldError error={error?.returnDate?.errors[0]} />
      </div>

      <div className="flex flex-col gap-1">
        <Label htmlFor="adults">Adults</Label>
        <Input
          name="adults"
          id="adults"
          type="number"
          min={0}
        />
        <FieldError error={error?.adults?.errors[0]} />
      </div>

      <div className="flex flex-col gap-1">
        <Label htmlFor="currencyCode">Currency</Label>
        <Select name="currencyCode">
          <SelectTrigger className="w-full">
            <SelectValue placeholder="Select a value..." />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="MXN">MXN</SelectItem>
            <SelectItem value="USD">USD</SelectItem>
            <SelectItem value="EUR">EUR</SelectItem>
          </SelectContent>
        </Select>
        <FieldError error={error?.currencyCode?.errors[0]} />
      </div>

      <div className="flex gap-1">
        <Checkbox
          id="nonStop"
          name="nonStop"
        />
        <Label htmlFor="nonStop">Non-stop</Label>
        <FieldError error={error?.nonStop?.errors[0]} />
      </div>

      <Button
        className="w-full"
        type="submit"
      >
        Search Flights
      </Button>
    </Form>
  );
}
