import {Button} from "@/components/ui/button";
import {DollarSign, Timer, ChevronUp, ChevronDown} from "lucide-react";
import {useState} from "react";
import {useFlightOffersContext} from "../../hooks/useFlightOffersContext";
import type {
  SortField,
  SortDirection,
} from "../../types/sorting/sort-config.type";

export function ResultsSortOptions() {
  const {setSortConfig} = useFlightOffersContext();
  const [sortState, setSortState] = useState<
    Record<SortField, SortDirection | null>
  >({
    price: null,
    duration: null,
  });

  const handleSortToggle = (field: SortField) => {
    const currentDirection = sortState[field];
    let newDirection: SortDirection | null;

    if (currentDirection === null) {
      newDirection = "asc";
    } else if (currentDirection === "asc") {
      newDirection = "desc";
    } else {
      newDirection = null;
    }

    const newSortState = {...sortState, [field]: newDirection};
    setSortState(newSortState);

    const newSortConfig = Object.entries(newSortState)
      .filter(([, direction]) => direction !== null)
      .map(([field, direction]) => ({
        field: field as SortField,
        direction: direction as SortDirection,
      }));

    setSortConfig(newSortConfig);
  };

  const getSortIcon = (field: SortField) => {
    const direction = sortState[field];
    if (direction === "asc") return <ChevronUp className="size-3" />;
    if (direction === "desc") return <ChevronDown className="size-3" />;
    return null;
  };

  const getButtonVariant = (field: SortField) => {
    return sortState[field] !== null ? "secondary" : "outline";
  };

  return (
    <div className="flex items-center gap-4">
      <p className="text-muted-foreground text-sm">Sort by:</p>

      <div className="flex items-center gap-2">
        <Button
          variant={getButtonVariant("price")}
          size="sm"
          onClick={() => handleSortToggle("price")}
          className="flex items-center gap-1"
        >
          <DollarSign className="size-4" />
          <span className="hidden md:block">Price</span>
          {getSortIcon("price")}
        </Button>

        <Button
          variant={getButtonVariant("duration")}
          size="sm"
          onClick={() => handleSortToggle("duration")}
          className="flex items-center gap-1"
        >
          <Timer className="size-4" />
          <span className="hidden md:block">Duration</span>
          {getSortIcon("duration")}
        </Button>
      </div>
    </div>
  );
}
