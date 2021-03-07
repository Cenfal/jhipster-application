import axios from 'axios';
import Vue from 'vue';
import router from '@/router';
import { localize } from 'vee-validate';
import { translateError } from '../mixins/componentActions';

const APIUrl = process.env.VUE_APP_API_URL;

export default {
  isPublic({ state }, pageName) {
    if (pageName.toLowerCase() === 'home') return true;
    const page = state.pageProperties.pages.find(page => page.pageName === pageName);
    return page && Boolean(page.isPublic);
  },
  handleError: ({ commit, dispatch, state }, err) => {
    const page = state.pageProperties;
    let e = undefined;
    const errors = page.errors ? page.errors[Vue.prototype.locale] : [];
    errors.forEach(pageError => {
      console.log(pageError, err);
      if (Array.isArray(err.message)) {
        if (pageError.error === err.message[0]) {
          e = pageError.message;
        }
      } else {
        if (pageError.error === err.message) {
          e = pageError.message;
        }
      }
    });
    console.error(err);
    if (err.formErr || e) {
      if (e) {
        if (err.cause) e = e + '<br>' + err.cause;
        commit('setModalOptions', { type: 'error', isActive: true, message: e });
      } else if (err.message && !Array.isArray(err.message) && page.errors[err.message]) {
        commit('setModalOptions', { type: 'error', isActive: true, message: page.errors[err.message] });
        return;
      } else if (Array.isArray(err.message)) {
        err.message[0] =
          err.message[0] === 'Email address is already in use.' ? 'Die E-Mail-Adresse wird bereits verwendet.' : err.message[0];
        commit('setModalOptions', { type: 'error', isActive: true, message: translateError(err.message[0]) });
      } else if (err.message) {
        commit('setModalOptions', { type: 'error', isActive: true, message: translateError(err.message) });
      } else {
        commit('setModalOptions', {
          type: 'error',
          isActive: true,
          message: 'Hoppla. Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut.',
        });
      }
    } else if (err.message) {
      if (err.cause) err.message = err.message + '<br>' + err.cause;
      commit('setModalOptions', { type: 'error', isActive: true, message: translateError(err.message) });
    } else {
      commit('setModalOptions', {
        type: 'error',
        isActive: true,
        message: 'Hoppla. Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut. 98',
      });
    }
    if (!err) {
      commit('setModalOptions', {
        type: 'error',
        isActive: true,
        message: 'Hoppla. Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut. 99',
      });
    }
  },

  setPageProperties: ({ commit }, properties) => {
    commit('setPageProperties', properties);
  },

  getTemplateData: ({ commit, dispatch, state }, url) => {
    return new Promise((resolve, reject) => {
      if (process.env.NODE_ENV === 'development') {
        state.pageProperties.testing = true;
      } else {
        state.pageProperties.testing = false;
      }
      let data = {
        campaignId: state.pageProperties.campaignId,
      };

      const siteLanguages = state.pageProperties.languages;
      if (siteLanguages && siteLanguages.length && window.location.pathname == '/') {
        const browserLang = navigator.language.toLowerCase();

        for (let i = 0; i < siteLanguages.length; i++) {
          if (browserLang.includes(siteLanguages[i].lang) && window.location.pathname != siteLanguages[i].url) {
            window.location.replace(siteLanguages[i].url);
            break;
          }
        }
      }

      data = { ...data, ...router.currentRoute.query };
      axios
        .post(`${APIUrl}/tempId`, data)
        .then(resp => {
          if (resp.data.response) {
            const data = {
              field: 'customerToken',
              value: resp.data.customerToken,
            };
            commit('setField', data);
          } else {
            dispatch('handleError', {
              message: `Unexpected Error`,
              cause: 'Failed to retrieve tempId',
            });
            reject(false);
          }
        })
        .catch(ex => {
          dispatch('handleError', {
            message: `Hoppla. Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut.`,
            cause: ` Customer Token::  ${ex.message}`,
          });
        });
      resolve(state.pageProperties);
      // }
      // axios.post(`${APIUrl}/getCampaign`, {
      //     url
      // }).then(res => {
      //     // eslint-disable-next-line
      //     // if (window.dataLayer) {
      //     //     // eslint-disable-next-line
      //     //     dataLayer.push(
      //     //         {
      //     //             'event_action': 'PageView'
      //     //         }
      //     //     )
      //     //     console.log("DATALAYER TRIGGER...")
      //     // }
      //     if (res.data.campaign) {
      //         commit('setPageProperties', res.data.campaign);
      //         commit('setCampaignId', res.data.campaign.campaignId);
      //         const data = {
      //             campaignId: res.data.campaign.campaignId
      //         }

      //         const siteLanguages = state.pageProperties.languages
      //         if(siteLanguages && siteLanguages.length && window.location.pathname == '/') {
      //             const browserLang = navigator.language.toLowerCase()

      //             for(let i=0; i< siteLanguages.length; i++) {
      //                 if(browserLang.includes(siteLanguages[i].lang) && window.location.pathname != siteLanguages[i].url) {
      //                     window.location.replace(siteLanguages[i].url)
      //                     break;
      //                 }
      //             }
      //         }

      //         axios.post(`${APIUrl}/tempId`, data)
      //             .then(resp => {
      //                 if (resp.data.response) {
      //                     const data = {
      //                         field: 'customerToken',
      //                         value: resp.data.customerToken
      //                     }
      //                     commit('setField', data)
      //                     // return true
      //                     return resolve(state.pageProperties)

      //                 } else if (!resp.data.response && !resp.data.geo) {
      //                     return resolve('GEOLIMIT')
      //                 } else {
      //                     resolve('NOT_FOUND')
      //                 }
      //             }, {
      //                 campaignId: 'ariel'
      //             }
      //             )
      //             .catch(ex => {
      //                 dispatch('handleError', {
      //                     message: `Hoppla. Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut.`,
      //                     cause: ` Get Template::  ${ex.message}`
      //                 })
      //             })
      //     } else {
      //         resolve('NOT_FOUND')
      //     }
      // })
    }).then(pageProperties => {
      const dictionary = pageProperties.dictionary;
      if (dictionary) {
        // merges with built-in phrases
        localize(dictionary);
      }
      if (pageProperties.header && pageProperties.header.properties.favicon) {
        updateFavicon(pageProperties.header.properties.favicon.imageSource);
      }
      return pageProperties;
    });
  },

  setTemplateData: ({ commit }, data) => {
    return new Promise((resolve, reject) => {
      // console.log("SET TEMPLATE DADTA", APIUrl, data)
      axios.post(`${APIUrl}/savePage`, data).then(res => {
        commit('setPageProperties', res.data);
        return resolve(res.data);
      });
    });
  },

  refreshTempId: ({ commit, getters }) => {
    return axios.post(`${APIUrl}/tempId`, { campaignId: getters.campaignId }).then(resp => {
      if (resp.data.response) {
        const data = {
          field: 'customerToken',
          value: resp.data.customerToken,
        };
        commit('setField', data);
      }
    });
  },

  showAlert: ({ commit }, data) => {
    // console.log("YAY!", data)
  },

  setComponentStatus: ({ commit }, { component, status }) => {
    commit('setComponentStatus', { component, status });
  },

  retrieveUserToken({ commit, dispatch, state }) {
    // eslint-disable-next-line
    // if (window.dataLayer) {
    //     // eslint-disable-next-line
    //     dataLayer.push(
    //         {
    //             'event_action': 'PageView'
    //         }
    //     )
    // }
  },

  sendFormData({ commit, dispatch }, data) {
    return new Promise((resolve, reject) => {
      if (window.nsId) {
        data.formData.nsId = window.nsId;
      }

      axios
        .post(data.apiUrl, data.formData)
        .then(res => {
          // eslint-disable-next-line
          // if (window.dataLayer) {
          //     // eslint-disable-next-line
          //     dataLayer.push(
          //         {
          //             'event_action': 'CompleteRegistration'
          //         }
          //     )
          // }
          if (data.triggers && data.triggers.neuStarTrigger) {
            // console.log("3. will send::: neutrigger", data.triggers)
            let a = window._agknEchoTag;
            if (a && data.n) {
              a.setBpId('firstp');
              a.addEchoKeyValue('event', 'submit');
              a.generateTag();
            }
          }
          if (res.data.response) {
            return resolve(res.data);
          } else {
            if (res.data.invalid_fields) {
              //janrain error
              dispatch('handleError', { message: res.data.invalid_fields[0] });
            } else if (res.data.message) {
              dispatch('handleError', { formErr: true, message: res.data.message });
            } else {
              dispatch('handleError', { formErr: true, code: res.data });
            }
            return reject(res.data);
          }
          // if (res.data.response) {
          //   commit('setReferenceNumber', res.data.referenceNumber)
          //   return { success: true }
          // } else {
          //   dispatch('handleError', res.data.error)
          //   return { success: false, error: res.data.error }
          // }
        })
        .catch(ex => {
          dispatch('handleError', {
            message: `Hoppla. Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut.`,
            cause: ` Send Form::  ${ex.message}`,
          });
          return { success: false, error: ex.message };
        });
    });
  },

  uploadProductPhotos({ getters, commit, dispatch }, data) {
    return new Promise((resolve, reject) => {
      // console.log("DATRA", data)
      const formData = new FormData();
      formData.append('productPhoto', data.image, 'productPhoto.png');
      formData.append('customerToken', getters.customerToken);
      formData.append('campaignId', getters.campaignId);
      formData.append('receiptToken', data.receiptToken);

      if (formData.customerToken == 1) {
        formData.append('token', data.token);
      }

      axios
        .post(data.apiUrl, formData)
        .then(res => {
          commit('setReferenceNumber', res.data.referenceNumber);
          return resolve(res.data);
          // if (res.data.response) {
          //   commit('setReferenceNumber', res.data.referenceNumber)
          //   return { success: true }
          // } else {
          //   dispatch('handleError', res.data.error)
          //   return { success: false, error: res.data.error }
          // }
        })
        .catch(ex => {
          dispatch('handleError', {
            message: `Hoppla. Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut.`,
            cause: ` Upload Product::  ${ex.message}`,
          });
          return { success: false, error: ex.message };
        });
    });
  },

  generateCode({ commit, dispatch, state }, data) {
    return new Promise((resolve, reject) => {
      data.formData.customerToken = state.customerToken;
      data.formData.campaignId = state.campaignId;
      data.formData.receiptToken = state.receiptToken;
      axios
        .post(data.apiUrl, data.formData)
        .then(res => {
          if (res.data.response) {
            return resolve(res.data);
          } else {
            dispatch('handleError', { formErr: true, message: res.data.message });
            return reject(res.data);
          }
          // if (res.data.response) {
          //   commit('setReferenceNumber', res.data.referenceNumber)
          //   return { success: true }
          // } else {
          //   dispatch('handleError', res.data.error)
          //   return { success: false, error: res.data.error }
          // }
        })
        .catch(ex => {
          dispatch('handleError', {
            message: `Hoppla. Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut.`,
            cause: ` Generate Code::  ${ex.message}`,
          });
          return { success: false, error: ex.message };
        });
    });
  },

  verifyCode({ commit, dispatch, state }, data) {
    return new Promise((resolve, reject) => {
      data.formData.customerToken = state.customerToken;
      data.formData.campaignId = state.campaignId;
      data.formData.receiptToken = state.receiptToken;

      axios
        .post(data.apiUrl, data.formData)
        .then(res => {
          // console.log(res.data)
          return resolve(res.data);
          // if (res.data.response) {
          //   commit('setReferenceNumber', res.data.referenceNumber)
          //   return { success: true }
          // } else {
          //   dispatch('handleError', res.data.error)
          //   return { success: false, error: res.data.error }
          // }
        })
        .catch(ex => {
          dispatch('handleError', {
            message: `Hoppla. Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut.`,
            cause: ` Verify Code::  ${ex.message}`,
          });
          return { success: false, error: ex.message };
        });
    });
  },

  sendProducts({ commit, dispatch, state }, data) {
    return new Promise((resolve, reject) => {
      data.formData.customerToken = state.customerToken;
      data.formData.campaignId = state.campaignId;
      axios
        .post(data.apiUrl, data.formData)
        .then(res => {
          if (res.data.response) return resolve(res.data);
          else dispatch('handleError', { message: res.data.message });
          // if (res.data.response) {
          //   commit('setReferenceNumber', res.data.referenceNumber)
          //   return { success: true }
          // } else {
          //   dispatch('handleError', res.data.error)
          //   return { success: false, error: res.data.error }
          // }
        })
        .catch(ex => {
          dispatch('handleError', {
            message: `Hoppla. Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut.`,
            cause: ` Send Products::  ${ex.message}`,
          });
          return { success: false, error: ex.message };
        });
    });
  },

  uploadReceipt({ getters, commit, dispatch }, data) {
    return new Promise((resolve, reject) => {
      const formData = new FormData();
      if (data.receiptToken) {
        formData.append('receiptToken', data.receiptToken);
      }
      formData.append('userToken', data.customerToken);
      formData.append('receipt', data.image, 'receipt.png');
      formData.append('subCampaignId', 'lenor-pm');
      formData.append('campaignId', data.campaignId);

      if (data.token) {
        formData.append('token', data.token);
      }

      // commit('toggleUploadingAnimation', true)
      // commit('setUploadPercentage', 0)

      return axios
        .post(`${data.apiUrl}`, formData, {
          // onUploadProgress: (progressEvent) => {
          //     const uploadPercentage = Math.round((progressEvent.loaded * 100) / progressEvent.total)
          //     commit('setUploadPercentage', uploadPercentage)
          // }
        })
        .then(resp => {
          if (resp.data.response !== 'ERR') {
            // commit('setReceiptUploaded', true)
            // commit('setReceipt', image)

            // if (resp.data.data && resp.data.processor !== 'manual') {
            // console.log(resp.data.receiptToken)
            commit('setReferenceNumber', resp.data.referenceNumber);
            commit('setReceiptToken', resp.data.receiptToken);
            // }

            return resolve(resp.data.receiptToken);
          } else {
            dispatch('handleError', resp.data.error);
          }
        })
        .catch(resp => {
          let errorCode = '';
          if (resp.response && resp.response.data) {
            errorCode = resp.response.data.error;
          }
          dispatch('handleError', {
            message: `Hoppla. Es ist ein Fehler aufgetreten. Bitte versuchen Sie es später erneut.`,
            cause: ` Upload Receipt::  ${errorCode}`,
          });
          // dispatch('handleError', errorCode)
        })
        .finally(() => {
          // commit('toggleUploadingAnimation', false)
        });
    });
  },

  onReceiptUploadSuccess({ commit }, data) {
    commit('setReceiptImage', data);
  },
  uploadBarcode($, data) {
    // Make a put request to add the barcode data with the given receipt token
    console.log('Actions', data);
    const apiUrl = 'https://national-cashback.justsnap.eu/api/v2/dashboard/receipt';
    axios
      .put(apiUrl, data, { headers: { 'Content-Type': 'application/json' } })
      .then(res => console.log('ACTIONS', res))
      .catch(err => console.log('ACTIONS', err));
  },
  uploadFile({ commit }, file) {
    if (file) commit('setFile', file);
  },
  setImgList({ commit }, list) {
    commit('setImgList', list);
  },
  getBarCode({ commit }, code) {
    commit('setBarCode', code);
  },
  handleProductCode({ commit }, value) {
    commit('setProductCode', value);
  },
  updateSearchStatus({ commit }, status) {
    commit('setSearchStatus', status);
  },
  updateScanner({ commit }, status) {
    commit('setScannerStatus', status);
  },
  findProducts({ commit }, array) {
    commit('setProducts', array);
  },
  removeProduct({ commit }, productId) {
    commit('deleteProduct', productId);
  },
  editQuantityProduct({ commit }, { product, action, maxProductCount }) {
    commit('setQuantityProduct', { product, action, maxProductCount });
  },
  editInputQuantityProduct({ commit }, { productId, count, maxProductCount }) {
    if (count === '0') {
      commit('deleteProduct', productId);
    } else {
      if (maxProductCount && count > maxProductCount) {
        commit('setModalOptions', {
          type: 'error',
          isActive: true,
          message: `Sie können nicht mehr als ${maxProductCount} Produkt auswählen.`,
        });
        commit('setInputQuantityProduct', { productId, count: maxProductCount });
        return;
      }
      commit('setInputQuantityProduct', { productId, count });
    }
  },
  searchProductByBarcode({ state, dispatch, commit }, { barcode, maxProductCount }) {
    let apiUrl = 'https://template-v2.justsnap.eu/api/searchBarcode';
    const isAllowed = maxProductCount ? maxProductCount > state.scannedProducts.length : true;

    // If barcode exists , exit method
    if (state.scannedProducts.find(item => item.ean === barcode)) return;

    if (/^[0-9]+$/.test(barcode) && isAllowed) {
      return new Promise((resolve, reject) => {
        axios
          .post(`${apiUrl}`, {
            campaignId: state.campaignId,
            barcode: barcode,
          })
          .then(res => {
            let status = res.data.result.length > 0 ? 'success' : 'not_found';
            dispatch('updateSearchStatus', status);

            // Check if the product has selected a valid item according to the selected product list
            const findSameCategory = state.scannedProducts.find(item => item.category === res.data.result[0].category);
            if (!findSameCategory) dispatch('findProducts', res.data.result);
            else {
              commit('setModalOptions', {
                type: 'error',
                isActive: true,
                message: `Sie können nicht mehr als ein Produkt aus derselben Kategorie eingeben`,
              });
              dispatch('updateSearchStatus', null);
            }

            if (status === 'not_found') {
              // The barcode is not P&G
              dispatch(
                'updateScannerErrorMessage',
                'Der Barcode scheint nicht zu einem P&G Produkt zu gehören. Glauben Sie, dass es sich um einen Fehler handelt?'
              );
              dispatch('setErrorActivate', true);
            } else {
              dispatch('setErrorActivate', false);
              console.log(res);
            }
            resolve(res.data);
          });
      });
    } else {
      if (!isAllowed) {
        commit('setModalOptions', {
          type: 'error',
          isActive: true,
          message: `Sie können nicht mehr als ${maxProductCount} Produkt auswählen.`,
        });
        dispatch('updateSearchStatus', null);
      } else {
        dispatch('updateSearchStatus', 'not_found');
        // The barcode is invalid
        dispatch(
          'updateScannerErrorMessage',
          'Der Barcode ist ungültig. Bitte versuchen Sie erneut, den Barcode einzugeben oder zu scannen. Glauben Sie, dass es sich um einen Fehler handelt?'
        );
        dispatch('setErrorActivate', true);
      }
    }
  },
  updateScannerErrorMessage({ commit }, error) {
    commit('setScannerError', error);
  },
  setErrorImages({ commit }, data) {
    console.log(data.posNum);
    commit('setErrorImages', data);
  },
  setErrorActivate({ commit }, data) {
    commit('setErrorActivate', data);
  },
  setReceiptBarcode({ commit }, data) {
    commit('setReceiptBarcode', data);
  },
  setIsReceiptImageChanged({ commit }, data) {
    commit('setIsReceiptImageChanged', data);
  },
  setConvertedImagePointsData({ commit }, data) {
    commit('setConvertedImagePointsData', data);
  },
  setFirstTimeUploading({ commit }, data) {
    commit('setFirstTimeUploading', data);
  },
};

export function updateFavicon(url) {
  const link = document.querySelector("link[rel*='icon']") || document.createElement('link');
  link.type = 'image/x-icon';
  link.rel = 'shortcut icon';
  link.href = url;
  document.getElementsByTagName('head')[0].appendChild(link);
}
