import {apiUrl} from "@/config/env.config";
import axios from "axios";

export const flightSearch = axios.create({
  baseURL: apiUrl,
});
