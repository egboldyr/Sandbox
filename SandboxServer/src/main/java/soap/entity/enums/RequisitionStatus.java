package soap.entity.enums;

/**
 * Created by EGBoldyr on 19.03.18.
 *
 * Тип данных "Статус заявки"
 *
 * - NEW      (Новая заявка с клиент-сайта)
 * - PROCESS  (Заявка которая находится в обработке)
 * - WAITING  (Заявка которая находится в режиме ожидания)
 * - COMPLETE (Заявка по которой был создан клиент)
 * - DONE     (Заявка закрыта)
 */

public enum RequisitionStatus {

    NEW,
    PROCESS,
    WAITING,
    COMPLETE,
    DONE

}
