const request = async (options, type) => {
    const headers = new Headers({
        'Content-Type': type ? 'application/json' : 'multipart/form-data',
    })

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);
    const response = await fetch(options.url, options);
    const json = await response.json();
    if (!response.ok) {
        return Promise.reject(json);
    }
    return json;
};

export function postReq(path, type, Data) {
    return request({
        url: path,
        method: 'POST',
        body: JSON.stringify(Data)
    }, type);
}


export function getReq(path, type, data) {
    let urlPath
    if (data !== undefined) urlPath = path + "/" + data
    else urlPath = path
    return request({
        url: urlPath,
        method: 'GET'
    }, type);
}