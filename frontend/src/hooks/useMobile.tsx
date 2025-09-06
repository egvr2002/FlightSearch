/**
 * This hook is part of the implementation of responsive components which use the
 * drawer component for mobile devices.
 *
 * In this project, it is used to build a responsive combobox as the documentation refers:
 * https://ui.shadcn.com/docs/components/combobox#:~:text=a%20new%20project-,Responsive,-You%20can%20create
 *
 * You can find the original implementation here:
 * https://github.com/shadcn-ui/ui/blob/main/apps/v4/registry/new-york-v4/hooks/use-mobile.ts
 *
 * Author: shadcn
 * Modified by: Emmanuel Valentin
 *
 */

import {useEffect, useState} from "react";

const MOBILE_BREAKPOINT = 768;

export function useIsMobile() {
  const [isMobile, setIsMobile] = useState<boolean | undefined>(undefined);

  useEffect(() => {
    const mql = window.matchMedia(`(max-width: ${MOBILE_BREAKPOINT - 1}px)`);
    const onChange = () => {
      setIsMobile(window.innerWidth < MOBILE_BREAKPOINT);
    };
    mql.addEventListener("change", onChange);
    setIsMobile(window.innerWidth < MOBILE_BREAKPOINT);
    return () => mql.removeEventListener("change", onChange);
  }, []);

  return !!isMobile;
}
