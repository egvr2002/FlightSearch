import {formatCurrency} from "@/lib/utils";
import type {Currency} from "../types/common/currency.type";

interface Props {
  grandTotalPrice: string;
  perTravelerPrice: string;
  currency: Currency;
  alignment?: "left" | "right";
}

export function PricingDisplay({
  grandTotalPrice,
  perTravelerPrice,
  currency,
  alignment = "right",
}: Props) {
  const alignmentClass = alignment === "right" ? "text-right" : "text-left";

  return (
    <div className={`space-y-4 ${alignmentClass}`}>
      <div className="text-primary font-semibold">
        <p>{formatCurrency(grandTotalPrice, currency)}</p>
        <p className="text-sm">total</p>
      </div>

      <div className="text-secondary">
        <p>{formatCurrency(perTravelerPrice, currency)}</p>
        <p className="text-sm">per traveler</p>
      </div>
    </div>
  );
}
