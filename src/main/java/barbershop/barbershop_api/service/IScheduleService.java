package barbershop.barbershop_api.service;

import barbershop.barbershop_api.entity.ScheduleEntity;

public interface IScheduleService {

    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final long id);

}
