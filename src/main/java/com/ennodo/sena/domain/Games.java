package com.ennodo.sena.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Games {
	List<List<Integer>> data;

	public List<String> mapTrio() {
		if (isNull(data)) throw new RuntimeException("Data couldn't null!");

		Map<String, Integer> trios = new LinkedHashMap<>();

		for (List<Integer> game: getData()) {
			for (int i = 0; i < 4; i++) {
				int u = i + 1;
				for (int z = u + 1; z < 6; z++) {
					Integer n1 = game.get(i);
					Integer n2 = game.get(u);
					Integer n3 = game.get(z);

					addTrio(trios, n1, n2, n3);
				}
			}
		}

		return sortTrios(trios);
	}

	private List<String> sortTrios(Map<String, Integer> trios) {
		List<Map.Entry<String, Integer>> listTrios = new ArrayList<>(trios.entrySet());
		listTrios.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

		List<String> resultTrios = new LinkedList<>();
		int count = 0;
		for (Map.Entry<String, Integer> trio: listTrios) {
			resultTrios.add(trio.getKey().substring(1));
			if (count++ > 9) break;
		}

		return resultTrios;
	}

	private void addTrio(Map<String, Integer> trios, Integer ...numbers) {
		String numbersConcat = Arrays.stream(numbers)
				.map(n -> String.format("%02d", n))
				.reduce("", (n1, n2) -> n1.concat("-").concat(n2));

		Integer count = trios.get(numbersConcat);

		if (isNull(count)) {
			trios.put(numbersConcat, 1);
		} else {
			trios.put(numbersConcat, count + 1);
		}
	}
}
