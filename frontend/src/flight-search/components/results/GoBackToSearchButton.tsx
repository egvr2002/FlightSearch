import {Link} from "react-router";

import {Button} from "@/components/ui/button";
import {ChevronLeft} from "lucide-react";

export function GoBackToSearchButton() {
  return (
    <Button
      variant="ghost"
      asChild
      className="w-fit"
    >
      <Link
        to="/"
        replace
      >
        <ChevronLeft className="size-6" />
        Return to Search
      </Link>
    </Button>
  );
}
