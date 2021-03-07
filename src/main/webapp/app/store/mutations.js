import Vue from 'vue';

export default {
  toggleEditMode: state => {
    state.isEditMode = !state.isEditMode;
  },
  setPageProperties: (state, properties) => {
    Vue.set(state, 'pageProperties', properties);
  },
  setCampaignId: (state, campaignId) => {
    state.campaignId = campaignId;
  },
  pageBuilderSelectField: (state, selected) => {
    state.pageBuilder.selectedField = selected;
  },
  setComponentStatus: (state, { component, status }) => {
    Vue.set(state.componentStatus, component, status);
  },
  setModalOptions: (state, { message = 'Ein Fehler ist aufgetreten', type = 'error', isActive }) => {
    state.modal.message = message;
    state.modal.type = type;
    state.modal.isActive = isActive;
  },
  setField: (state, { field, value }) => {
    Vue.set(state, field, value);
  },
  setStep: (state, { pageStatus }) => {
    state.pageStatus = pageStatus;
  },
  setReceiptToken: (state, receiptToken) => {
    state.receiptToken = receiptToken;
  },
  setReceiptImage: (state, receiptImage) => {
    state.receiptImage = receiptImage;
  },
  setFile: (state, file) => {
    state.file = file;
  },
  setImgList: (state, list) => {
    state.imgList = list;
  },
  setBarCode: (state, data) => {
    state.barcode = data;
    state.productCode = data;
  },
  setScannerStatus: (state, status) => {
    state.scannerActive = status;
  },
  setProductCode: (state, value) => {
    state.productCode = value;
  },
  setSearchStatus: (state, value) => {
    state.searchStatus = value;
  },
  setProducts: (state, array) => {
    let listOfProducts = array.map(el => ({
      ...el,
      id: Math.random(),
      count: 1,
    }));
    state.scannedProducts = [...state.scannedProducts, ...listOfProducts];
    state.scannedProducts = state.scannedProducts.reduce((acc, val) => {
      const product = acc.find(el => el.name === val.name && el.barcode === val.barcode);
      if (product) {
        product.count = +product.count < 99 ? product.count + 1 : product.count;
        return acc;
      }
      return [...acc, val];
    }, []);
  },
  resetProducts: state => {
    state.scannedProducts = [];
  },
  resetReceiptFile: state => {
    state.receiptFile = null;
    console.log('MUTATE', state.receiptFile);
  },
  deleteProduct: (state, productId) => {
    state.scannedProducts = state.scannedProducts.filter(pr => pr.id !== productId);
  },
  setQuantityProduct: (state, { product, action, maxProductCount }) => {
    let productIndex = state.scannedProducts.findIndex(pr => pr.id === product.id);
    product = state.scannedProducts[productIndex];
    product.count =
      action === 'add' ? (product.count < maxProductCount || !maxProductCount ? +product.count + 1 : +product.count) : +product.count - 1;
    product.count = +product.count < 0 ? 0 : product.count;
    product.count = +product.count > 99 ? 99 : product.count;
  },
  setInputQuantityProduct: (state, { productId, count }) => {
    let productIndex = state.scannedProducts.findIndex(pr => pr.id === productId);
    state.scannedProducts[productIndex].count = count === '' ? 1 : +count <= 0 ? 1 : +count > 99 ? 99 : +count;
  },
  setDonates: (state, donates) => {
    state.donates = donates;
  },
  setScannerError: (state, error) => {
    state.errorMessage = error;
  },
  setErrorImages: (state, data) => {
    let imgListLen = state.errorImageList.length;
    console.log(data.img);
    console.log(data.posNum);
    state.errorImageList[data.posNum - 1] = data.img;
  },
  setErrorActivate: (state, data) => {
    state.errorActivate = data;
  },
  setReceiptBarcode: (state, data) => (state.receiptBarcode = data),
  setReferenceNumber: (state, data) => (state.referenceNumber = data),
  setIsReceiptImageChanged: (state, data) => (state.isReceiptImageChanged = data),
  setConvertedImagePointsData: (state, data) => (state.convertedImagePointsData = data),
  setFirstTimeUploading: (state, data) => (state.firstTimeUploading = data),
  setRemoveLogo: (state, data) => (state.removeLogo = data),
};
