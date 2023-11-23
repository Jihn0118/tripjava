import {localAxios} from "@/util/http-commons";

const local = localAxios();

async function getHeartStatement(param, success, fail) {
    await local.get("/heart/heartState", {params: param}).then(success).catch(fail);
}

async function doHeart(param, success, fail) {
    await local.post("/heart", param).then(success).catch(fail);
}

async function cancelHeart(param, success, fail) {
    await local.delete("/heart", {params: param}).then(success).catch(fail);
}


async function getTotalHeartCount(contentId, success, fail) {
    await local.get(`/heart/count/${contentId}`).then(success).catch(fail);
}

export {
    getHeartStatement, doHeart, cancelHeart, getTotalHeartCount
};