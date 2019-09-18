import fetch from 'utils/fetch';


export function upload(obj) {
  return fetch({
    url: '/zuul/api/admin/file/upload',
    method: 'post',
    data: obj
  });
}

export function add(obj) {
  return fetch({
    url: '/api/admin/file/add',
    method: 'post',
    data: obj
  });
}

export function queryList(obj) {
  return fetch({
    url: '/api/admin/file/fileinfopage',
    method: 'get',
    params: obj
  });
}

export function delObj(id) {
  return fetch({
    url: '/api/admin/file/delete/' + id,
    method: 'delete'
  })
}

export function downFile(obj) {
  return fetch({
    url: '/api/admin/file/downloadFile',
    method: 'get',
    params: obj,
    responseType: 'blob',
    headers: {
            'Accept': 'application/octet-stream'
        }
    
  })
}

export function getFileInfoById(id) {
  return fetch({
    url: '/api/admin/file/fileInfoDetail/'+id,
    method: 'get'
  })
}

export function getLineData(id) {
  return fetch({
    url: '/api/admin/file/lineData/'+id,
    method: 'get'
  })
}
export function getFileCount() {
  return fetch({
    url: '/api/admin/file/getfileinfocount',
    method: 'get'
  })
}

export function getAimsByName(obj) {
  return fetch({
    url: '/api/admin/aims/getbynamelike',
    method: 'get',
    params: obj
  });
}