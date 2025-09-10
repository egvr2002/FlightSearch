import {Search} from "lucide-react";
import {Button} from "../ui/button";
import {Card, CardContent} from "../ui/card";
import {Link} from "react-router";

interface Props {
  title: string;
  description: string;
  Icon: React.JSX.ElementType;
}

export function EmpyFlightsResult({title, description, Icon}: Props) {
  return (
    <Card>
      <CardContent>
        <div className="flex flex-col items-center justify-center gap-4">
          <div className="text-center">
            <Icon className="text-muted-foreground mx-auto h-12 w-12" />
            <h2 className="mb-2 text-lg font-semibold">{title}</h2>
            <p className="text-muted-foreground text-center text-sm">
              {description}
            </p>
          </div>
          <Button
            variant="outline"
            asChild
          >
            <Link
              to="/"
              replace
            >
              <Search className="size-4" />
              Return to Search
            </Link>
          </Button>
        </div>
      </CardContent>
    </Card>
  );
}
