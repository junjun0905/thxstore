<template>
  <transition name="slide-vertical">
    <div v-show="isMainDrawerOpend" class="drawer-container">
      <nav class="navigation-icon-wrapper">
        <div ref="time-icon" class="drawer-item active" @click="moveToPage('feed')">
          <span class="drawer-icon-wrapper">
            <awesome icon="meteor"></awesome>
          </span>
          <span class="detail-guide-text">타임 딜!</span>
        </div>
        <div ref="profile-icon" class="drawer-item" @click="moveToPage('user')">
          <span class="drawer-icon-wrapper">
            <awesome icon="user-circle"></awesome>
          </span>
          <span class="detail-guide-text">프로필</span>
        </div>
        <div
          v-if="getUserInfo.role === 'ROLE_MANAGER'"
          ref="store-icon"
          class="drawer-item"
          @click="moveToPage('store')"
        >
          <span class="drawer-icon-wrapper">
            <awesome icon="store"></awesome>
          </span>
          <span class="detail-guide-text">내 가게</span>
        </div>
        <div v-if="getUserInfo.role === 'ROLE_ADMIN'" ref="admin-icon" class="drawer-item" @click="moveToPage('admin')">
          <span class="drawer-icon-wrapper">
            <awesome icon="clipboard-list"></awesome>
          </span>
          <span class="detail-guide-text">관리자 대시보드</span>
        </div>
        <div ref="admin-icon" class="drawer-item" @click="logout()">
          <span class="drawer-icon-wrapper">
            <awesome icon="sign-out-alt"></awesome>
          </span>
          <span class="detail-guide-text">로그아웃</span>
        </div>
      </nav>
    </div>
  </transition>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex';
import { clearLocalStorageItem, USER_INFO, TOKEN } from '@/utils/webStorage';
export default {
  computed: {
    ...mapGetters(['isMainDrawerOpend', 'getUserInfo']),
  },
  methods: {
    ...mapMutations(['toggleMainDrawerOpen', 'setToken', 'clearUserInfo']),
    moveToPage(name) {
      if (this.$router.history.current.fullPath.includes(name)) return;
      this.toggleMainDrawerOpen();
      this.$router.push({ name });
    },
    logout() {
      // 로그아웃을 할 때는 로컬 스토리지를 비워야한다.

      clearLocalStorageItem(USER_INFO);
      clearLocalStorageItem(TOKEN);

      // vuex도 비운다.
      this.setToken('');
      this.clearUserInfo();

      // drawer를 닫는다.
      this.toggleMainDrawerOpen();
      this.moveToPage('login');
    },
  },
};
</script>

<style lang="scss" scoped>
.slide-vertical-enter-active,
.slide-vertical-leave-active {
  transition-duration: 0.5s;
  transition-property: height, opacity, transform;
  transition-timing-function: cubic-bezier(0.55, 0, 0.1, 1);
  overflow: hidden;
}
.slide-vertical-leave-active,
.slide-vertical-enter {
  opacity: 0;
  @include transform(translate(0, -10em));
}

.drawer-container {
  position: fixed;
  z-index: 2;
  top: 50px;
  right: 0px;
  box-shadow: 0 10px 5px rgba(0, 0, 0, 0.2);
  @include mobile() {
    top: 44px;
  }
  @include xs-mobile() {
    width: 100%;
    top: 44px;
  }
}
.navigation-icon-wrapper {
  @include transition(all 0.5s);
  // header 크기가 변함에 따라 top 변경해줘야함.
  width: 100%;
  font-size: 1.2rem;
  background-color: white;
  border: none;
}
.drawer-icon-wrapper {
  display: inline-block;
  width: 35px;
  svg {
    width: 25px;
    height: 25px;
    @include xs-mobile() {
      width: 22px;
      height: 22px;
    }
  }
}
.drawer-item {
  @include flexbox;
  @include align-items(center);
  @include transition(all 0.5s);
  color: black;
  padding: 10px;
  cursor: pointer;
  border: 1px solid white;
  border-bottom: 1px solid $gray400;
  &:hover {
    color: white;
    background-color: $blue600;
    border: 1px solid $blue600;
  }
  @include pc() {
    padding: 5px;
  }
  @include mobile() {
    padding: 5px;
    font-size: 1.1rem;
  }
  @include xs-mobile() {
    padding: 5px;
    font-size: 1rem;
  }
}
</style>
