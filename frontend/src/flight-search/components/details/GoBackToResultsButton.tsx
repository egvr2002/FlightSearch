import {Button} from "@/components/ui/button";
import {ChevronLeft} from "lucide-react";
import {useNavigate} from "react-router";

export function GoBackToResultsButton() {
  const navigate = useNavigate();

  const onNavigate = () => {
    navigate(-1);
  };

  return (
    <Button
      variant="ghost"
      className="w-fit cursor-pointer"
      onClick={onNavigate}
    >
      <ChevronLeft className="size-6" />
      Return to Search
    </Button>
  );
}
