import {Button} from "./components/ui/button";

export default function App() {
  return (
    <div className="flex min-h-dvh flex-col items-center justify-center gap-2">
      <h1 className="text-4xl">Hello World!</h1>
      <Button
        onClick={() => alert("Button clicked!")}
        variant="default"
      >
        Click Me
      </Button>
    </div>
  );
}
