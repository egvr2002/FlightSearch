/// <reference types="vitest" />

import path from "path";
import {defineConfig} from "vite";
import {configDefaults} from "vitest/config";

import react from "@vitejs/plugin-react-swc";
import tailwindcss from "@tailwindcss/vite";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), tailwindcss()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
    },
  },
  test: {
    environment: "jsdom",
    globals: true,
    coverage: {
      exclude: [
        ...(configDefaults.coverage?.exclude || []),
        "src/components/ui/**",
      ],
    },
  },
});
