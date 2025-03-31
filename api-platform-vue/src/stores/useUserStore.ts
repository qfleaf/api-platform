import { defineStore } from "pinia";
import type { LoginUserVO } from "../types";

export const useUserStore = defineStore("user", {
    state: () => ({ currentUser: {} as LoginUserVO }),
    getters: {
        getCurrentUser: (state) => {
            return state.currentUser;
        },
    },
    actions: {},
})