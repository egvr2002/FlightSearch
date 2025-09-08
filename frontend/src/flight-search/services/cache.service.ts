export function getCacheKey<T>(prefix: string, data: T): string {
  return prefix + JSON.stringify(data);
}

export function getCachedData<T>(key: string): T | null {
  const item = sessionStorage.getItem(key);
  if (!item) return null;
  try {
    return JSON.parse(item) as T;
  } catch {
    return null;
  }
}

export function setCachedData<T>(key: string, data: T): void {
  sessionStorage.setItem(key, JSON.stringify(data));
}

export function clearCache(prefix: string): void {
  const keys = Object.keys(sessionStorage);
  keys.forEach((key) => {
    if (key.startsWith(prefix)) {
      sessionStorage.removeItem(key);
    }
  });
}
