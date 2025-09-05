import {ToggleGroup, ToggleGroupItem} from "@/components/ui/toggle-group";
import {DollarSign, Timer} from "lucide-react";

export function ResultsSortOptions() {
  return (
    <div className="flex items-center gap-2">
      <p className="text-muted-foreground text-sm">Sort by:</p>
      <ToggleGroup
        type="multiple"
        size="sm"
        onValueChange={(values) => console.log(values)}
      >
        <ToggleGroupItem value="price">
          <DollarSign className="size-4" />{" "}
          <span className="hidden md:block">Price</span>
        </ToggleGroupItem>

        <ToggleGroupItem value="duration">
          <Timer className="size-4" />{" "}
          <span className="hidden md:block">Time</span>
        </ToggleGroupItem>
      </ToggleGroup>
    </div>
  );
}
