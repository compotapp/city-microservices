package com.pot.app.productionofgoods.service.work;

import com.pot.app.productionofgoods.entity.Employee;
import lombok.extern.slf4j.Slf4j;

import static com.pot.app.productionofgoods.enums.EmployeeStatus.FREE;
import static com.pot.app.productionofgoods.enums.EmployeeStatus.RELAX;

@Slf4j
public class ExperienceProcessor {

    /**
     * Обрабатывает получение опыта сотрудником:
     * - начисляет опыт
     * - проверяет и повышает уровень
     * - списывает очки действий
     * - переводит в отдых при отсутствии очков
     */
    public static void processExperienceGain(Employee employee, int earnedExp) {
        if (earnedExp > 0) {
            addExperience(employee, earnedExp);
            checkAndIncreaseLevel(employee);
        }

        deductActionPoints(employee, earnedExp);
        setToFreeOrRelax(employee);

        log.debug("{}: {} получил опыт: {}, текущий уровень: {}, очки: {}, статус {}",
                employee.getType().getTitle(), employee.getNumber() , earnedExp, employee.getLevel(), employee.getPoints(), employee.getStatus().getTitle());
    }

    private static void addExperience(Employee employee, int exp) {
        employee.setExp(employee.getExp() + employee.getLevel() * exp);
    }

    /**
     * Списывает очки действий (минимум 1 за действие)
     */
    private static void deductActionPoints(Employee employee, int earnedExp) {
        int pointsToDeduct = earnedExp == 0 ? 1 : earnedExp;
        employee.setPoints(employee.getPoints() - pointsToDeduct);
    }

    /**
     * Проверяет и повышает уровень, если накоплено достаточно опыта
     * Формула: следующий уровень требует 2^level опыта
     */
    private static void checkAndIncreaseLevel(Employee employee) {
        if (hasEnoughExpForLevel(employee)) {
            employee.setLevel(employee.getLevel() + 1);
            log.debug("{}: {} повысил уровень до {}",
                    employee.getType().getTitle(), employee.getNumber(), employee.getLevel());
        }
    }

    /**
     * Переводит сотрудника в статус отдыха, если закончились очки действий
     */
    private static void setToFreeOrRelax(Employee employee) {
        employee.setStatus(employee.getPoints() > 0 ? FREE : RELAX);
    }

    /**
     * Проверяет, достаточно ли опыта для уровня
     */
    private static boolean hasEnoughExpForLevel(Employee employee) {
        return employee.getExp() >= getExpRequiredForLevel(employee.getLevel());
    }

    /**
     * Возвращает необходимый опыт для указанного уровня
     */
    private static int getExpRequiredForLevel(int level) {
        return (int) Math.pow(2, level);
    }
}
