export function saveInSessionStorage<T = unknown>(key: string, payload: T) {
  const parsedPayload = JSON.stringify(payload);
  sessionStorage.setItem(key, parsedPayload);
}

export function getFromSessionStorage<T = unknown>(key: string) {
  const data = sessionStorage.getItem(key);
  if (!data) return;
  const parsedData = JSON.parse(data) as T;
  return parsedData;
}

export function removeFromSessionStorage(key: string) {
  sessionStorage.removeItem(key);
}
