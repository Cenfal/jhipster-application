export default {
  isEditMode: state => state.isEditMode,
  getPageProperties: state => state.pageProperties,
  getPages: state => Object.keys(state.pageProperties),
  selectedPagebuilderItem: state => state.pageBuilder.selectedField,
  customerToken: state => state.customerToken,
  receiptToken: state => state.receiptToken,
  campaignId: state => state.campaignId,
  scannedProducts: state => state.scannedProducts,
  currentPage: state => () => {
    if (state.pageStatus) {
      return state.pageStatus.current.pageName;
    }
    return undefined;
  },
  hasUnresolvedDependencies(state) {
    return fields => {
      if (!fields || !fields.length) {
        return false;
      }
      const resolvedFields = fields.map(field => state.componentStatus[field]).filter(field => field);

      return resolvedFields.length !== fields.length;
    };
  },
  hasSomeOfDependencies(state) {
    return fields => {
      if (!fields || !fields.length) {
        return false;
      }

      return !fields.map(field => state.componentStatus[field]).some(field => field === true);
    };
  },
  getErrorImageList: state => state.errorImageList,
  getErrorMessage: state => state.errorMessage,
  getErrorActivate: state => state.errorActivate,
  getReceiptBarcode: state => state.receiptBarcode,
  getReferenceNumber: state => state.referenceNumber,
  isReceiptImageChanged: state => state.isReceiptImageChanged,
  convertedImagePointsData: state => state.convertedImagePointsData,
  firstTimeUploading: state => state.firstTimeUploading,
  removeLogo: state => state.removeLogo,
};
