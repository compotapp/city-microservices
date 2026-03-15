package com.pot.app.productionofnumbers.service;

import com.pot.app.core.dto.production.of.numbers.NumberSequenceRequest;
import com.pot.app.productionofnumbers.entity.NumberSequence;
import com.pot.app.productionofnumbers.entity.NumberSequence.SequenceType;
import com.pot.app.productionofnumbers.repository.NumberSequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pot.app.productionofnumbers.entity.NumberSequence.SequenceType.valueOfPrefix;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class NumberSequenceServiceImpl implements NumberSequenceService {

    private final static String FORMAT = "%s-%04d";
    private final NumberSequenceRepository repository;

    @Override
    @Transactional
    public List<String> generate(NumberSequenceRequest request) {
        return generateNumbers(valueOfPrefix(request.type().getPrefix()), request.count());
    }

    private List<String> generateNumbers(SequenceType type, int count) {
        NumberSequence sequence = findByTypeOrCreate(type);
        List<Integer> numbers = sequence.getNextNumbers(count);
        repository.save(sequence);
        return numbers.stream()
                .map(number -> format(FORMAT, type.getPrefix(), number))
                .toList();
    }

    private NumberSequence findByTypeOrCreate(SequenceType type) {
        return repository.findByType(type)
                .orElseGet(() -> { //Если нет - создаем новую
                    return repository.save(new NumberSequence(type));
                });
    }
}
