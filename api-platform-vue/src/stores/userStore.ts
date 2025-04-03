import { defineStore } from "pinia";
import type { UserLoginRequest, UserLoginResponse } from "../types";
import { doUserLogin, doUserLogout } from "../services/users";

export const useUserStore = defineStore("user", {
    state: () => ({
        auth: {} as UserLoginResponse
    }),
    getters: {
        getToken: (state) => {
            return state.auth?.token;
        },
        getRole: (state) => {
            return state.auth?.currentAuthority;
        },
        isLogin: (state) => {
            return !!state.auth?.token;
        }
    },
    actions: {
        login(user: UserLoginRequest) {
            return doUserLogin(user)
                .then((data) => {
                    this.auth = data;
                })
                .catch((err) => {
                    throw err;
                });
        },
        logout() {
            doUserLogout();
            this.auth = {} as UserLoginResponse;
        },
    },
    persist: true,
})