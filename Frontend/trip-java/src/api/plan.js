import { localAxios } from "@/util/http-commons";

const local = localAxios();

// 여행 계획 등록 API
function registerTravelPlans(plan, success, fail) {
  local.post(`/plan`, plan).then(success).catch(fail);
}

function getTravelPlans(userId,success,fail){
  local.get(`plan/list/${userId}`).then(success).catch(fail);
}
export { registerTravelPlans,getTravelPlans };
