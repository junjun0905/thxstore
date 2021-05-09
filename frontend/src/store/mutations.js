export default {
  setTempUserInfo(state, tmpUserInfo) {
    state.tempUserInfo = Object.assign(state.tempUserInfo, tmpUserInfo);
  },
  setUserInfo(state, userInfo) {
    state.userInfo = Object.assign(state.userInfo, userInfo);
  },
  setToken(state, token) {
    state.token = token;
  },
  toggleMainDrawerOpen(state) {
    state.mainDrawerOpen = !state.mainDrawerOpen;
  },
  setSpinnerState(state, status) {
    state.spinnerOn = status;
  },
};
