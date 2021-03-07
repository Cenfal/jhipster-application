// For using this component use this.showModal('message here');
// This will be open Error Modal.
// If you want to show success model add second parameter as 'success'
// For hiding this modal you can use this.hideModal();

<template>
  <div>
    <b-modal class="modal" v-model="modal.isActive" :id="modalComputed" hide-footer hide-header centered>
      <div class="closeButtonWrapper">
        <button type="button" class="close" @click="closeModal()">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="iconWrapper d-block text-center">
        <template v-if="modal.type != 'custom'">
          <img v-if="modal.type == 'success'" class="icon" src="../assets/icons/success.svg" alt="success" />
          <img v-else class="icon" src="../assets/icons/risk-icon.svg" alt="warning" />
        </template>
      </div>
      <div class="body" v-html="modal.message"></div>
    </b-modal>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {};
  },
  methods: {
    closeModal() {
      this.hideModal();
    }
  },
  computed: {
    ...mapState({
      modal: state => state.modal
    }),
    modalComputed () {
      return this.modal.type === "custom" ? 'modalCustom' : 'modal'
    }
  }
};
</script>

<style lang="scss">
#modal___BV_modal_content_ {
  border-radius: 0;
  border: unset;
}

#modalCustom___BV_modal_content_ {
  border: 4px solid var(--color2);
  background-color: beige
}
</style>

<style lang="scss" scoped>
.modal-body {
  display: flex;
  flex-direction: column;
  .closeButtonWrapper {
    position: absolute;
    top: 0;
    right: 0;
    margin: 15px;
  }
  .iconWrapper {
    img {
      height: 80px;
      width: 80px;
    }
    display: flex;
    justify-content: center;
  }
  .body {
    font-family: inherit;
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-left: 20px;
    padding-right: 20px;
    margin-top: 15px;
  }
}
</style>