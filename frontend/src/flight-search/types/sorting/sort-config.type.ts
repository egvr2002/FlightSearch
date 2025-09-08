export type SortField = "price" | "duration";
export type SortDirection = "asc" | "desc";

export type SortOption = {
  field: SortField;
  direction: SortDirection;
};

export type SortConfig = SortOption[];
