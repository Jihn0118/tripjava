import {localAxios} from "@/util/http-commons";

const local = localAxios();

// const root = "/attraction"

function listSidoName(success, fail) {
    local.get(`/attraction/sido`).then(success).catch(fail);
}

function listInfo(param, success, fail) {
    local.get("/attraction", {params: param}).then(success).catch(fail);
}

function infoDetail(infoId, success, fail) {
    local.get(`/attraction/detail/${infoId}`)
        .then(success)
        .catch(fail);
}

function allContentFindById(param, success, fail) {
    local.get("/attraction/attractions", {params: param})
        .then(success)
        .catch(fail);
}

export {
    listSidoName, listInfo, infoDetail, allContentFindById
};