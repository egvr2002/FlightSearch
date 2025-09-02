interface Props extends React.ComponentProps<"p"> {
  error?: string;
}

export function FieldError({error, ...props}: Props) {
  if (!error) return null;

  return (
    <p
      aria-live="polite"
      className="text-destructive text-sm"
      {...props}
    >
      * {error}
    </p>
  );
}
