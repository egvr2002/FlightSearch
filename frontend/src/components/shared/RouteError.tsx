import {
  useLocation,
  useNavigate,
  useNavigation,
  useRouteError,
} from "react-router";
import {Card, CardContent} from "../ui/card";
import {Frown, Loader2} from "lucide-react";
import {Button} from "../ui/button";
import {GoBackToSearchButton} from "@/flight-search/components/results/GoBackToSearchButton";

export function RouteError() {
  const error = useRouteError();
  const navigate = useNavigate();
  const {pathname, search} = useLocation();
  const {state} = useNavigation();

  const isLoading = state === "loading";

  const reloadPage = async () => {
    navigate(`${pathname}${search}`);
  };

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between gap-2">
        <GoBackToSearchButton />
      </div>
      <Card>
        <CardContent>
          <div className="flex flex-col items-center justify-center gap-4">
            <div className="text-center">
              <Frown className="text-muted-foreground mx-auto h-12 w-12" />
              <h2 className="mb-2 text-lg font-semibold">Oops!</h2>
              <p className="text-muted-foreground text-center text-sm">
                Sorry, an unexpected error has occurred.
              </p>
              <p className="text-muted-foreground text-center text-sm">
                <i>{(error as Error).message || String(error)}</i>
              </p>
            </div>
            <Button
              variant="outline"
              onClick={reloadPage}
              disabled={isLoading}
            >
              {isLoading && <Loader2 className="mr-2 h-4 w-4 animate-spin" />}
              {isLoading ? "Reloading..." : "Reload Page"}
            </Button>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}
