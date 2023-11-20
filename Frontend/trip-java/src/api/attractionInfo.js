import {localAxios} from "@/util/http-commons";

const local = localAxios();
// const root = "/attraction"

function listSidoName(success, fail) {
    local.get(`/attraction/sidoName`).then(success).catch(fail);
}

export {
    listSidoName
};